# Designdocument
##Activities en Methods
*StartScreen*

	Alleen een plaatje en een Button (geen returnwaarde) om naar ImageSelection te gaan
*ImageSelection*

	Wordt een tableView met een vaste Button voor Difficulty De foto’s  komen in de cellen te staan. 
	Als er op wordt geklikt linkt hij door naar GamePlay.
	Onthoud de Difficulty en de foto
*GamePlay*

	Maak een extra layout in het midden waarop het antwoord te zien is. 
	Zorg dat deze na 3 seconden verdwijnt.
	Methods om de Bitmap in te laden (return Bitmap), 
	op te slaan en in stukken te hakken (loadBitmap, saveBitmap en chopBitmap
	Method play die het spel speelt; Zorg dat hij weet waar het lege vakje is en controleer geldige move.
	Bij geldige move switch het lege vakje en het fotovakje en +1 bij de movecounter variabele.
	Method winner die controleert of het spel gewonnen is.
*YouWin*

	Button die linkt naar ImageSelection
	Textview die feliciteert en de movecounter geeft
	De foto die opgelost is
*Difficulty*

	Start op in een klein scherm (niet fill parent)
	en laat je kiezen uit 3 Buttons voor de verschillende moeilijkheidsgraden

##API’s

De app moet kunnen werken op android 2.1 (API 7) en hoger.
Ik denk geen extra ondersteunende libraries nodig te hebben,
omdat de menu functie en de verschillende layouts al vrij lang ondersteund worden.

![mockup] (https://github.com/eddywallyfan/nPuzzle10812806/blob/master/doc/Mockup.jpg)
