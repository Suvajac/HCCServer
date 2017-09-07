package net.etfbl.hcc;

import net.etfbl.hcc.data.dao.mysql.MySQLGostDAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello".hashCode());
		System.out.println("Hello millss");
		System.out.println(new MySQLGostDAO().getKorisnik("ljubisamilincic").getPrezime());
	}

}
