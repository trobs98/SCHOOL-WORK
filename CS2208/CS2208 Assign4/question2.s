			AREA question2, CODE, READONLY
			ENTRY
	
			LDR 	r0,varX				;load variable 'x' into register r0
			ADR		r13, STACK 			;loads the address in memory where the stack will start
	
			BL		PARABOLA			;branch to the PARABOLA function where 'ax^2 + bx + c' is calculated using 'x' stored in register r0 and store the address of the next instruction into register r14
			MOV 	r1,r0,LSL#1 		;multiplies the value returned by the PARABOLA function by 2 and stores it in register r1

LOOP 		B 		LOOP				;end of the program

PARABOLA 	STMFD	r13!,{r1,r2,r14} 	;saves the contents of registers r1,r2 and r14 before starting the subroutine
	
			LDR 	r1,conA				;load constant 'a' into register r1
			MUL		r2,r1,r0			;Multiply 'a' by 'x' which will then store the result 'ax' into register r2
	
			LDR 	r1,conB				;load constant 'b' into register r1, replacing constant 'a'
			ADD 	r2,r2,r1 			;add b' to 'ax' to get 'ax+b', which will then be stored into register r2
			MUL 	r0,r2,r0			;multiply 'ax+b' by 'x' to get 'ax^2 + bx', which will then be stored into register r2
	
			LDR 	r1,conC				;load constant 'c' into register r1, replacing constant 'b'
			ADD 	r0,r0,r1 			;add 'c' to 'ax^2 + bx' to get 'y = ax^2 + bx + c', which is then stored into register r1
	
			LDR 	r1,conD				;load constant 'd' into register r1, replacing constant 'c'
	
			CMP 	r0,r1				;compare 'y' and 'd'
			MOVGT 	r0,r1				;if 'y' > 'd' then replace 'y' with 'd' in register r1 
	
			LDMFD	r13!,{r1,r2,r15}	;restores the contents of registers r1 and r2, and places the branch address (r14 contents) into the program counter register so it will return back to the program
	
			AREA question2, DATA, READONLY

conA		DCD		5					;constant 'a'
conB		DCD		6					;constant 'b'
conC		DCD		7					;constant 'c'
conD		DCD 	90					;constant 'd'
varX		DCD 	3					;variable 'x'
			space	0xFF				;allocates empty space in memory to seperate the constants/variables with the stack
			align						;aligns the memory address so the STACK is correctly aligned on a 32-bit boundary	
STACK 		DCD 	0x00 				;space to be used as a stack to store previous info of registers r1 and r2
			END