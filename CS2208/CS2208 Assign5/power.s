		AREA power, CODE, READWRITE
		ENTRY

X		EQU 4							;passed-by-value for 'x'
N		EQU 3							;passed-by-value for 'n'
	
MAIN	
		ADR r13, STK					;put the stack location into r13 so it can be used
		MOV r0, #X						;places the value of 'x' into r0 to be passed to the POWER function
		MOV r1, #N						;places the value of 'n' into r1 to be passed to the POWER function
		STMFD r13!, {r0-r1}				;pass the values 'x' and 'n' onto the stack
		SUB r13, r13, #4				;leaves an open area of memory above parameters for the return value
		BL POWER						;call the POWER function
		
		LDR r2, [r13], #12				;when POWER function returns the calculated value, the value is at top of stack which will be loaded into r2
		ADR r3, RESULT					;places the memory address of RESULT into r3
		STR r2, [r3]					;stores the calculated result of x^n into the memory of RESULT
	
FINISH	B FINISH						;end of the program

POWER	
		STMFD r13!, {r0-r1, r11, r14}	;push the registers onto the stack to be modified, as well as the frame pointer and the stack pointer
		MOV r11, r13					;sets the current frame pointer to the top of the stack
		SUB r13, r13, #4				;creates space in memory for the variable y
		LDR r1, [r11, #24]				;load the passed-by-value of 'n' 
		CMP r1, #0						;checks to see if 'n' is zero
		MOVEQ r1, #1					;if 'n' is zero, we are going to return one (r1 contains value to be returned)
		BEQ RETURN						;branch to RETURN
		
		LDR r0, [r11, #20]				;'n' will not be zero, so continue by loading the passed-by-value of 'x'
		TST r1, #1						;test the last bit of 'n' to see if 'n' is even or odd
		BNE ODD							;if 'n' is odd then we branch to ODD
		B EVEN							;if 'n' is not odd then we branch to EVEN
		
ODD	
		SUB r1, r1, #1					;decrease 'n' by 1
		STMFD r13!, {r0-r1}				;pass the values 'x' and 'n' onto the stack
		SUB r13, r13, #4				;creates space in memory for the return value
		BL POWER						;call the POWER function
		
		LDR r1, [r11, #-16]				;get the return value from the above recursive call to POWER
		MUL r1, r0, r1					;multiply 'x' times the previous return value that is stored in r1
		B RETURN						;branch to RETURN
		
EVEN	
		ASR r1, #1						;divide 'n' by 2 by right shifting 1
		STMFD r13!, {r0-r1}				;pass the values 'x' and 'n' onto the stack
		SUB r13, r13, #4				;creates space in memory for the return value
		BL POWER						;call the POWER function
		
		LDR r0, [r11, #-16]				;get the return value from the above recursive call to POWER
		STR r0, [r11, #-4]				;store the return value into a local variable 'y' on the stack
		MUL r1, r0, r0					;multiply y by itself anf store it in r1 (r1 contains the return value) and then continue into the RETURN block

RETURN	
		STR r1, [r11, #16]				;store [r1] into the return value spot on the stack
		MOV r13, r11					;move the stack pointer back to the current frame pointer
		LDMFD r13!, {r0-r1, r11, r15}	;restore the modified registers along with the frame pointer, and move the link-register into the program counter
		
		AREA power, DATA, READONLY

		SPACE 550						;plenty of space for the stack in case the returned values become very large or there are alot of them
		ALIGN							;aligns the memory so that the stack and result are 

STK		DCD	0x00						;Location of the stack in memory
RESULT	DCD	0x00						;Where the result of x^n will be stored after it is calculated	
		
		END
		