package net.etfbl.hcc;

import java.sql.Date;

import net.etfbl.hcc.data.dao.mysql.MySQLGostDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLPopustDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLRecepcionarDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLStavkaDAO;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Recepcionar;
import net.etfbl.hcc.model.Soba;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.model.Usluga;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello".hashCode());
		System.out.println("Hello millss");
		Gost g=new Gost( "usernamePaaa", " ime", " prezime", " brojTelefona", " lozinkaHash");
		g.setSoba(new Soba(101,5,5,5));

		System.out.println(new MySQLGostDAO()
				.getKorisnik("draganbunic").getRacun().getPopust().getProcenat());
		//System.out.println(new MySQLGostDAO()
			//	.dodaj(g,new Date(31321),new Date(21313)));
	}

}
