			AREA question1, CODE, READWRITE
			ENTRY
	
			ADR 	r0,STRING1							;loads the address of STRING1 into r0 so we can loop through the string
			ADR 	r1,STRING2							;loads the address of STRING2 into r1 so we can store charachters into it
			MOV 	r2,#0x00							;count to step through the charachters of STRING1 (load counter)
			MOV 	r3,#0x00 							;count used for storing the byte into the correct location of STRING2 (store counter)
	
START		LDRB 	r4,[r0,r2] 							;load the charchter in STRING1 given its position by the load counter and store it in r4
			CMP 	r4,#0x00							;compares the charchter with the 'null' charachter to see if it is the end of the string
			BEQ 	FINISH 								;if the charachter is 0x00 it means we have reached the end of the string so it will branch to the end of the program
	
			CMP 	r4,#0x74 							;check to see if the charachter is a 't'
			BEQ 	letterT								;if it is a 't' then it will branch to letterT 
	
			STRB 	r4,[r1,r3] 							;store the charachter in r4 (that is not a 't') into the memory of STRING2 in the position given by the store counter
			ADD 	r2,r2,#1 							;add 1 to the load counter so we can proceed to the next charachter in the string
			ADD 	r3,r3,#1							;add 1 to the store counter so we store the next charachter in the right location in memory
			B 		START								;go back to the START loop and check the next charachter
	
letterT		SUB 	r2,r2,#1							;subtract the load counter by 1 and check to see if the 't' is the first letter of a word or not
			LDRB 	r8,[r1,r2] 							;load the previously stored charachter into register r8 to make sure that the 't' is not in a word such as 'breathe'
			ADD 	r2,r2,#2							;restore the load counter to its previous state
			
			CMP		r8,#0x00							;checks to see if the previous charachter before the 't' is null
			BEQ		CONTINUE 							;if it is then we branch to continue
			
			CMP 	r8,#0x20							;if it is not null then we check to see if the charachter before the 't' is a space
			BNE 	FIX									;if it is NOT then branch to the FIX statement to store the 't'
			
CONTINUE	LDRB 	r5,[r0,r2] 							;if the charachter before the 't' is a space then we load the next charachter after the 't' into register r5
			CMP 	r5,#0x68							;check to see if the charachter is an 'h'
			BEQ 	letterH								;if it is an 'h' then we branch to letterH 
	
			STRB 	r4,[r1,r3]							;if it is NOT an 'h' then we store the letter 't' in the next location in STRING2
			ADD 	r3,r3,#1							;add 1 to the store counter so we store the next charachter in the correct location in memory
			B 		START								;branch back to the start to check the next charachter
	
letterH		ADD 	r2,r2,#1 							;add 1 to the load counter so we load the next charachter after the 'h'
			LDRB 	r6,[r0,r2]							;load the charachter after the 'h' of STRING1 into register r6
	
			CMP 	r6,#0x65							;check to see if the current charachter is an 'e'
			BEQ 	CHECK								;if it is an 'e' then we branch to CHECK 
	
			STRB 	r4,[r1,r3]							;if it is NOT an 'e', then we store the 't' into the location given by store counter into the memory given by STRING2
			ADD 	r3,r3,#1							;update the store counter so we can store the next charachter in the correct location in memory
			SUB 	r2,r2,#1							;update the load counter so we can store the 'h' next time we enter the START loop 
			B 		START								;branch back to the start loop
	
CHECK		ADD 	r2,r2,#1							;add 1 to the load counter so we can load the next charachter after the 'e'
			LDRB 	r7,[r0,r2]							;load the next charachter after the 'e'
			CMP 	r7,#0x00							;check to see if the charachter after the 'e' is null
			BEQ 	FINISH								;if it is null, then we branch to FINISH to end the program  
	
			CMP 	r7,#0x20							;check to see if the charachter after the 'e' is a space
			BEQ 	START								;if it is a space, then we branch back to the start so that 'the' is not stored in STRING2
	
			STRB 	r4,[r1,r3]							;if it is NOT a space, then we store the 't' into STRING2 at the position given by the store pointer
			SUB 	r2,r2,#2							;update the load pointer so that we look at the 'h' after the 't' next time we branch to the START
			ADD 	r3,r3,#1							;add 1 to the store pointer so we store the next charachter in the correct location in memory
			B 		START								;branch back to START
	
FIX			STRB 	r4,[r1,r3]							;store the 't' into STRING2 at the position given by the store pointer (we did this because the 't' was in a word such as breathe)
			ADD 	r3,r3,#1							;add 1 to the store pointer so we store the next charachter in the correct location in memory
			B 		START								;branch back to the start so we can continue stepping through the charachters of STRING1
	
FINISH 		B 		FINISH								;end of program

			AREA question1, DATA, READONLY
	
STRING1 	DCB 	"and the man said they must go" 	;String1
Eos 		DCB 	0x00								;end of string1
STRING2 	SPACE 	0xFF								;just allocating 255 bytes
	
			END