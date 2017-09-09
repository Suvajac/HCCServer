use hotelcc;

delimiter $$
create procedure insert_into_gost(
	in varUser varchar(50),
	in varIme varchar(20),
    in varPrezime varchar(20),
    in varBrTel varchar(30),
    in varLozinka varchar(512),
	in varBrojSobe int,
    in varDatumOd date,
    in varDatumDo date)
begin
	declare idRac int default 0;
	
    insert into racun(Placen) values (0);
    
	select max(IdRacuna) into idRac from racun;
    
	insert into korisnik(Username,Ime,Prezime,
		BrojTelefona,LozinkaHash) values(varUser,varIme,varPrezime,varBrTel,varLozinka);
	insert into gost(Username,IdRacuna) values(varUser,idRac);
	insert into registracija(Username,DatumOd,DatumDo,BrSobe) values(varUser,varDatumOd,varDatumDo,varBrojSobe);
   
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
	in varid int(50),
	in varDate date,
    in varVrijeme time
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into sporttermin(IdTermina) values(varid);
	else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into sporttermin(IdTermina) values(a);
    end if;
	
end$$
delimiter ;

delimiter $$
create procedure insert_into_wellnesstermin(
	in varid int(50),
	in varDate date,
    in varVrijeme time
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into wellnesstermin(IdTermina) values(varid);
	else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into wellnesstermin(IdTermina) values(a);
    end if;
	
end$$
delimiter ;

-- korisnicki nalog
drop user if exists 'mils'@'localhost';
create user 'mils'@'localhost' identified by 'mils';
grant select, insert, update, delete on hotelcc.* to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_gost to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_recepcionar to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_sporttermin to 'mils'@'localhost';
grant execute on procedure hotelcc.insert_into_wellnesstermin to 'mils'@'localhost';
grant select on mysql.proc to 'mils'@'localhost';
flush privileges;
