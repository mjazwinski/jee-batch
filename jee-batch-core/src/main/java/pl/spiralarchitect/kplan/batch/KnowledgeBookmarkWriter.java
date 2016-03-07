package pl.spiralarchitect.kplan.batch;

import java.util.List;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class KnowledgeBookmarkWriter extends AbstractBatchlet {

	@Inject
	private KnowledgeResources resources;

	@Override
	public String process() throws Exception {
		List<KnowledgeResource> resourcesList = resources.getResources();
		System.out.println("------resourcesList> " + resourcesList.size());
		for (KnowledgeResource resource : resourcesList) {
			System.out.println("storing resource: " + resource);
		}
		return "END";
	}

}
