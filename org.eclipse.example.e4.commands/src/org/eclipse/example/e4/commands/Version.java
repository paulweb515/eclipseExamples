package org.eclipse.example.e4.commands;

public class Version implements Comparable<Version> {

	private int major;
	private int minor;
	private int micro;
	private String qualifier;
	private String verString;

	public Version(int major, int minor, int micro, String qualifier) {
		this.major = major;
		this.minor = minor;
		this.micro = micro;
		this.qualifier = qualifier;
	}

	@Override
	public int compareTo(Version o) {
		if (o == this) {
			return 0;
		}
		int rc = major - o.major;
		if (rc != 0) {
			return rc;
		}
		rc = minor - o.minor;
		if (rc != 0) {
			return rc;
		}
		rc = micro - o.micro;
		if (rc != 0) {
			return rc;
		}
		if (qualifier == null) {
			return o.qualifier == null ? 0 : -1;
		}
		if (o.qualifier == null) {
			return 1;
		}
		return qualifier.compareTo(o.qualifier);
	}

	@Override
	public String toString() {
		return getVersion();
	}

	public String getVersion() {
		if (verString == null) {
			StringBuilder buf = new StringBuilder();
			buf.append(major).append('.').append(minor).append('.')
					.append(micro);
			if (qualifier != null) {
				buf.append('.').append(qualifier);
			}
			verString = buf.toString();
		}
		return verString;
	}
}
