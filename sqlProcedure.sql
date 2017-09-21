use hotelcc;

delimiter $$
create procedure insert_into_gost(
	in varUser varchar(50),
	in varIme varchar(20),
    in varPrezime varchar(20),
    in varBrTel varchar(30),
    in varLozinka varchar(512),
    in varIdRac int(11))
begin
	insert into korisnik(Username,Ime,Prezime,
		BrojTelefona,LozinkaHash) values(varUser,varIme,varPrezime,varBrTel,varLozinka);
	insert into gost(Username,IdRacuna) values(varUser,varIdRac);
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_racun(
	out rez int(11) )
begin
    insert into racun(Placen) values (0);
	select max(IdRacuna) into rez from racun;
end$$
delimiter ;

delimiter $$
create procedure insert_into_recepcionar(
	in varUser varchar(50),
	in varIme varchar(20),
    in varPrezime varchar(20),
    in varBrTel varchar(30),
    in varLozinka varchar(512)
	)
begin
	
	insert into korisnik(Username,Ime,Prezime,
		BrojTelefona,LozinkaHash) values(varUser,varIme,varPrezime,varBrTel,varLozinka);
	insert into recepcionar(Username) values(varUser);
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_sporttermin(
	in varid int(11),
	in varDate date,
    in varVrijeme time,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into sporttermin(IdTermina) values(varid);
        set rez=varid;
	else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into sporttermin(IdTermina) values(a);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_wellnesstermin(
	in varid int(11),
	in varDate date,
    in varVrijeme time,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
    
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into wellnesstermin(IdTermina) values(varid);
		set rez=varid;
    else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into wellnesstermin(IdTermina) values(a);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_sobnausluga(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varTip varchar(20),
	out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into sobnausluga(IdUsluge,Tip) values(varid,varTip);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into sobnausluga(IdUsluge,Tip) values(a,varTip);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_uslugarestorana(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdStola int(11),
    in varVrijeme varchar(20),
	out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into uslugarestorana(IdUsluge,BrojStolica,Vrijeme) values(varid,varIdStola,varVrijeme);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into uslugarestorana(IdUsluge,BrojStolica,Vrijeme) values(a,varIdStola,varVrijeme);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_wellnessusluga(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdTermina int(11),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into wellnessusluga(IdUsluge,IdTermina) values(varid,varIdTermina);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into wellnessusluga(IdUsluge,IdTermina) values(a,varIdTermina);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_sportusluga(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdTermina int(11),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into sportusluga(IdUsluge,IdTermina) values(varid,varIdTermina);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into sportusluga(IdUsluge,IdTermina) values(a,varIdTermina);
        set rez=a;
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_oglas(
	in varid int(11),
    in varTime timestamp,
	in varPoruka varchar(256),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into oglas(IdOglasa,Datum,Poruka) values(varid,varTime,varPoruka);
		set rez=varid;
	else
		insert into oglas(Datum,Poruka) values(varTime,varPoruka);
		select max(IdOglasa) into a from oglas;
		set rez=a;
    end if;
end$$
delimiter ;

delimiter $$
create procedure insert_into_proizvod(
	in varid int(11),
    in varNaziv varchar(150),
	in varCijena double,
    in varTip varchar(20),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into proizvod(IdProizvoda,Naziv,Cijena,Tip) values(varid,varNaziv,varCijena,varTip);
		set rez=varid;
	else
		insert into proizvod(Naziv,Cijena,Tip) values(varNaziv,varCijena,varTip);
		select max(IdProizvoda) into a from proizvod;
		set rez=a;
    end if;
end$$
delimiter ;

delimiter $$
create procedure insert_into_obavjestenje(
	in varid int(11),
    in varTekst varchar(500),
	in varDatum timestamp,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into obavjestenje(IdObavjestenja,Tekst,Datum,Procitano) values(varid,varTekst,varDatum,0);
		set rez=varid;
	else
		insert into obavjestenje(Tekst,Datum,Procitano) values(varTekst,varDatum,0);
		select max(IdObavjestenja) into a from obavjestenje;
		set rez=a;
    end if;
end$$
delimiter ;

delimiter $$
create procedure insert_into_proizvodirestoran(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM proizvodirestoran where IdProizvoda=varPro and IdUslugaRestorana=varUsl)) 
		then
			update proizvodirestoran set Kolicina=Kolicina+1 where IdProizvoda=varPro and IdUslugaRestorana=varUsl;
		else	
			insert into proizvodirestoran(IdProizvoda,IdUslugaRestorana) values (varPro,varUsl);
        end if;
end$$
delimiter ;

delimiter $$
create procedure insert_into_opremasportusluga(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM opremasportusluga where IdSportskeOpreme=varPro and IdUsluge=varUsl)) 
		then
			update opremasportusluga set Kolicina=Kolicina+1 where IdSportskeOpreme=varPro and IdUsluge=varUsl;
		else	
			insert into opremasportusluga(IdSportskeOpreme,IdUsluge) values (varPro,varUsl);
        end if;
end$$
delimiter ;

delimiter $$
create procedure insert_into_proizvodisoba(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM proizvodisoba where IdProizvoda=varPro and IdSobneUsluge=varUsl)) 
		then
			update proizvodisoba set Kolicina=Kolicina+1 where IdProizvoda=varPro and IdSobneUsluge=varUsl;
		else	
			insert into proizvodisoba(IdProizvoda,IdSobneUsluge) values (varPro,varUsl);
        end if;
end$$
delimiter ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_usluga`(
	in varNaziv varchar(20),
    in varCijena decimal(6,2),
	out rez int(11) )
begin
    insert into usluga(Naziv,Cijena) values (varNaziv,varCijena);
	select max(IdUsluge) into rez from usluga;
end ;;
DELIMITER ;

-- korisnicki nalog
drop user if exists 'mils'@'localhost';
create user 'mils'@'localhost' identified by 'mils';
grant select, insert, update, delete on hotelcc.* to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_gost to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_racun to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_recepcionar to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_sporttermin to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_wellnesstermin to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_sobnausluga to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_uslugarestorana to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_wellnessusluga to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_sportusluga to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_oglas to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_proizvod to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_obavjestenje to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_proizvodirestoran to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_opremasportusluga to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_proizvodisoba to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_usluga to 'mils'@'localhost';

grant select on mysql.proc to 'mils'@'localhost';
flush privileges;
