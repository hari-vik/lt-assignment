INSERT INTO 
	name_basics(nconst, primaryname, birthyear, deathyear, primaryprofession, knownfortitles)
	VALUES ('nm0005690', 'William K.L. Dickson', 1860, 1935, 'cinematographer,director,producer', 'tt1428455,tt1496763,tt0219560,tt0308254');
INSERT INTO 
	name_basics(nconst, primaryname, birthyear, deathyear, primaryprofession, knownfortitles)	
	VALUES ('nm1588970', 'Carmencita', 1860, 1935, 'soundtrack', 'tt0000001,tt0057728');

INSERT INTO title_basics(
	tconst, titletype, primarytitle, originaltitle, isadult, startyear, endyear, runtimeminutes, genres)
	VALUES ('tt0453643', 'short', 'Carmencita', 'Carmencita', 0, 1897, null, null, 'Short');

INSERT INTO title_basics(
	tconst, titletype, primarytitle, originaltitle, isadult, startyear, endyear, runtimeminutes, genres)
	VALUES ('tt0000001', 'short', 'Carmencita', 'Carmencita', 0, 1894, null, null, 'Documentary,Short');

INSERT INTO title_principals(
	tconst, ordering, nconst, category, job, characters)
	VALUES ('tt0000001', 3, 'nm1588970', 'self', null,null);

INSERT INTO title_principals(
	tconst, ordering, nconst, category, job, characters)
	VALUES ('tt0000001', 3, 'nm0005690', 'director', null,null);


INSERT INTO public.title_crew(
	tconst, directors, writers)
	VALUES ('tt0000001', 'nm0005690', null);

