package com.packtpub.jakarta.patterns.structural;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "parent_id", "content" }) })
public class Leaf extends Component {

	private String content;

	public Leaf() {
		super();
	}

	@Column(nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * The <code>parent</code> must be overrided in order to be able to declare
	 * <code>uniqueConstraints</code> on the class (see this class definition),
	 * otherwise it will say "Unable to find column with logical name
	 * <code>Leaf.parent_id</code>"
	 */
	@Override
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true)
	public Composite getParent() {
		return this.parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

	/* ==================== */
	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		} else if (!(obj instanceof Composite)) {
			return false;
		} else {
			Composite composite = (Composite) obj;
			return new EqualsBuilder().append(this.parent, composite.getParent())
					.append(this.content, composite.getName()).isEquals();
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.parent).append(this.content).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("parent", this.parent.getName())
				.append("name", this.content).toString();
	}
}