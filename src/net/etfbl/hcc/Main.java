package net.etfbl.hcc;

import java.sql.Date;

import net.etfbl.hcc.data.dao.mysql.MySQLGostDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLPopustDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLRecepcionarDAO;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Recepcionar;
import net.etfbl.hcc.model.Soba;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello".hashCode());
		System.out.println("Hello millss");
		Gost g=new Gost( "usernamePaaa", " ime", " prezime", " brojTelefona", " lozinkaHash");
		g.setSoba(new Soba(101,5,5,5));
		System.out.println(new MySQLPopustDAO()
				.dodaj(new Popust(7,4.4,true)));

	}

}
