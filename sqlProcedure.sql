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
