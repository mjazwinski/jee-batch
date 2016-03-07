package pl.spiralarchitect.kplan.batch;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class BatchImportService
 */
@Stateless
@LocalBean
public class BatchImportService {

	public void triggerImport() {
		ensureDirExists("workDir");
		File resourcesDir = ensureDirExists("import");
		File articlesFile = new File("src/main/resources/META-INF/articles.txt");
		Path source = Paths.get(articlesFile.getAbsolutePath());
		try {
			Files.copy(source, Paths.get(resourcesDir.getAbsolutePath()).resolve("articles.txt"), StandardCopyOption.REPLACE_EXISTING);
			startBatch(resourcesDir);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to start batch: " + e.getMessage());
		}
	}

	private File ensureDirExists(String dirName) {
		File resourcesDir = new File(dirName);
		if (!resourcesDir.exists()) {
			resourcesDir.mkdirs();
		}
		return resourcesDir;
	}

	private void startBatch(File resourcesDir) {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		props.setProperty(KnowledgeImportParamters.RESOURECE_DIR.getParameterName(), resourcesDir.getAbsolutePath());
		long jobId = jobOperator.start("batch-books", props);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
