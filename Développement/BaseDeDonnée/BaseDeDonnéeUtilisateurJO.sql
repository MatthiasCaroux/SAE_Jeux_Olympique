-- ajout des roles :

create role administrateur;

create role organisateur;

create role journaliste;


-- ajout des droits aux roles :

grant select on BaseDeDonneesJO.* to journaliste;

grant update, create, drop, select, on BaseDeDonneesJO.* to administrateur;

grant select, create on BaseDeDonneesJO.EPREUVE to organisateur;



-- ajout des utilisateurs :
create user mathiasAdmin;

create user carelOrga;

create user niksanJournaliste;


-- ajout des roles aux utilisateurs :

grant administrateur to mathiasAdmin;

grant organisateur to carelOrga;

grant journaliste to niksanJournaliste;
