package n1ejercicio5;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ObjEx5 implements Serializable {

	private String attribute;

	public ObjEx5(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return attribute;
	}

	@Override
	public String toString() {
		return "ObjEx5 --> Attribute: " + attribute + ".";
	}

}
