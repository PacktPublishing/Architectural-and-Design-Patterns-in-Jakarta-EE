package com.packtpub.jakarta.patterns.structural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
* XXX: This class can't be abstract because otherwise I get:
* org.hibernate.InstantiationException: Cannot instantiate abstract class
* or interface my.package.Component
* There is no hibernate sql weird log anway
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Component {

protected Long id;
protected Composite parent;		

public Component() {
super();
this.parent = null;
}

@Id @GeneratedValue(strategy=GenerationType.AUTO)
public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

/**
* The 1:N association is bidirectional, and the <code>ManyToOne</code> side thus
* must declare <code>mappedBy="parent"</code>,
* and so the <code>parent</code> is actually needed on the super class because otherwise it will say
* <em>""Colum Component.parent not found"</em> or something like that
*
* @return
*/
@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST}, optional=true)
public Composite getParent(){
return this.parent;
}

public void setParent(Composite parent){
this.parent = parent;
}
}