package com.packtpub.jakarta.patterns.structural;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "parent_id", "name" }) })
public class Composite extends Component {

	private String name;
	private Set<Component> children;

	public Composite() {
		super();
		this.children = new HashSet<>();
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The <code>parent</code> must be overrided in order to be able to declare
	 * <code>uniqueConstraints</code> on the class (see this class definition),
	 * otherwise it will say "Unable to find column with logical name
	 * <code>Composite.parent_id</code>"
	 */
	@Override
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true)
	public Composite getParent() {
		return this.parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<Component> getChildren() {
		return children;
	}

	public void setChildren(Set<Component> children) {
		this.children = children;
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
			return new EqualsBuilder().append(this.parent, composite.getParent()).append(this.name, composite.getName())
					.isEquals();
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.parent).append(this.name).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("parent", this.parent.getName())
				.append("name", this.name).toString();
	}
}