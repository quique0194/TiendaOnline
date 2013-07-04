package pe.edu.ucsp.oms.domain;

import java.io.Serializable;


public interface BaseEntity<PK extends Number> extends Serializable {
	PK getId();
	void setId(PK id);
}
