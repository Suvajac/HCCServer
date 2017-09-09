package net.etfbl.hcc;

import java.util.Date;

import net.etfbl.hcc.data.dao.mysql.MySQLProizvodDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSportTerminDAO;
import net.etfbl.hcc.model.SportTermin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello".hashCode());
		System.out.println("Hello millss");
		/*Gost g=new Gost( "usernamePaaa", " ime", " prezime", " brojTelefona", " lozinkaHash");
		g.setSoba(new Soba(101,5,5,5));


		System.out.println(new MySQLGostDAO()
				.getKorisnik("ljubisamilincic").getDatumDo());*/
		System.out.println(new MySQLSportTerminDAO().provjeriTermin(new SportTermin(0,new Date(),"14:00")));

	}

}
