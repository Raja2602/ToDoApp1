package todoapp;

import java.util.Objects;

public class TaskBean implements Comparable<TaskBean> {
	private String name;
	private String desc;
	private String cr_dt;
	private String end_dt;
	private String priority;
	private String status;
	private String tags;
    //
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "name=" + name + ", desc=" + desc + ", cr_dt=" + cr_dt + ", end_dt=" + end_dt + ", priority=" + priority
				+ ", status=" + status + ", tags=" + tags + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cr_dt, desc, end_dt, name, priority, status, tags);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(cr_dt, other.cr_dt) && Objects.equals(desc, other.desc)
				&& Objects.equals(end_dt, other.end_dt) && Objects.equals(name, other.name)
				&& Objects.equals(priority, other.priority) && Objects.equals(status, other.status)
				&& Objects.equals(tags, other.tags);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCr_dt() {
		return cr_dt;
	}

	public void setCr_dt(String cr_dt) {
		this.cr_dt = cr_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int compareTo(TaskBean t2) {
		return this.name.compareTo(t2.getName());
	}
}
