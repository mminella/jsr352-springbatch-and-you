package org.springframework.batch.jsr.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.batch.api.chunk.ItemReader;

public class ListItemReader implements ItemReader {

	private List<String> items;
	private int index = -1;
	private boolean hasCheckpointed = false;

	public ListItemReader() {
		items = new ArrayList<String>();

		for(int i = 0; i < 20; i++) {
			items.add(String.valueOf(i));
		}
	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		if(checkpoint != null) {
			index = Integer.parseInt((String) checkpoint);
		}
	}

	@Override
	public void close() throws Exception {
	}

	@Override
	public Object readItem() throws Exception {
		if(hasCheckpointed) {
			throw new RuntimeException("This was suposed to happen");
		}

		index++;

		if(index < items.size()) {
			return items.get(index);
		} else {
			return null;
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		hasCheckpointed = true;
		return String.valueOf(index);
	}
}
