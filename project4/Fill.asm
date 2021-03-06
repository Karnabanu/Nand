// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

	// Put your code here.
	(function)
	@KBD
	D=M
	@Draw
	D			;JEQ
	D=-1
	(Draw)
	@R1
	M=D
	@8191
	D=A
	@SCREEN
	D=A+D
	@R0
	M=D
	(loop)
	@R1
	D=M
	@R0
	A=M
	M=D
	@R0
	D=M
	@SCREEN
	D=D-A
	@END
	D			;JEQ
	@R0
	M=M-1			
	@loop
	0			;JMP
	
	(END)
	@function
	0			;JMP
