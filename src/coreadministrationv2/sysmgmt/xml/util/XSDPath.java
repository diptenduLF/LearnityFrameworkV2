package coreadministrationv2.sysmgmt.xml.util;

public enum XSDPath {
	APPLICATION_TEMPLATE_XSD("/xsd/application_template.xsd"), THEME_XSD("/xsd/theme.xsd"), ROLE_XSD("/xsd/Interfacerole.xsd"), MANIFEST_XSD(
			"/xsd/Manifest.xsd"), INTERFACE_XSD("/xsd/Interface.xsd"), SCXML_XSD("/xsd/scxml.xsd"), CUSTOMCOMPONENT_XSD("/xsd/CustomComponent.xsd");

	private String schemaPath;

	private XSDPath(String schemaPath) {
		this.schemaPath = schemaPath;
	}

	public String getSchemaPath() {
		return schemaPath;
	}

}
