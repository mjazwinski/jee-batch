package pl.spiralarchitect.kplan.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class KnowledgeResourceProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object item) throws Exception {
		String resourceAsString = (String)item;
		return KnowledgeResource.fromString(resourceAsString);
	}

}
