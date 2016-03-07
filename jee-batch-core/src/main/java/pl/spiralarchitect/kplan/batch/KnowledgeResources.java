package pl.spiralarchitect.kplan.batch;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class KnowledgeResources {

	private List<KnowledgeResource> resources = new ArrayList<>();

	public void addResource(KnowledgeResource resource) {
		resources.add(resource);
	}
	
	public List<KnowledgeResource> getResources() {
		return resources;
	}
	
}
