package org.eclipse.e4.examples.di.product.parts;

class AuthorCompany {
	public String name;
	public String company;

	public AuthorCompany(String n, String c) {
		name = n;
		company = c;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AuthorCompany) {
			return ((AuthorCompany) o).name.equals(name)
					&& ((AuthorCompany) o).company.equals(company);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * 87 + company.hashCode();
	}
}