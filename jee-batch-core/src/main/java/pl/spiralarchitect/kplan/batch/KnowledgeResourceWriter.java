package pl.spiralarchitect.kplan.batch;

import java.util.List;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class KnowledgeResourceWriter extends AbstractItemWriter {

	@Inject
	private JobContext jobContext;
	
	@Inject
	private KnowledgeResources resources;
	
	@Override
	public void writeItems(List<Object> items) throws Exception {
		items.stream().forEach(resource -> resources.addResource((KnowledgeResource) resource));
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
		jobParameters.setProperty("next", "STORE");
	}

}
