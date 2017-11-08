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
	@Key
	D=M
	@Draw
	D			;JNE
	@1
	D=A
	(Draw)
	@R1
	M=D
	@8192
	D=A
	@R0
	M=D
	(loop)
	@R0
	D=M
	@END
	D			;JEQ
	D=SCREEN+D
	A=D
	@R2=1

	@R0
	M=M-1
	@loop
	0			;JMP
	
	(END)
	@function
	0			;JMP
