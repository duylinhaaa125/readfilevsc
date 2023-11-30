package Demo;

import java.util.List;

public class FolderModel {
	private int id;
	private String name;
	private int parentId;
	private List<FolderModel> childrent;
	
	public FolderModel() {
		super();
	}

	public FolderModel(int id, String name, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public List<FolderModel> getChildrent() {
		return childrent;
	}

	public void setChildrent(List<FolderModel> childrent) {
		this.childrent = childrent;
	}

	@Override
	public String toString() {
		return "FolderModel [id=" + id + ", name=" + name + ", parentId=" + parentId + ", childrent=" + childrent + "]";
	}


}
