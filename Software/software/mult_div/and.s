	.file	"main.c"
	.option nopic
	.text
	.section	.text.exception_handler,"ax",@progbits
	.align	2
	.globl	exception_handler
	.type	exception_handler, @function
exception_handler:
	lw	a5,8(zero)
	ebreak
	.size	exception_handler, .-exception_handler
	.section	.text.startup.main,"ax",@progbits
	.align	2
	.globl	main
	.type	main, @function
main:
	addi	sp,sp,-16
	li	a5,91
	sb	a5,13(sp)
	li	a5,49
	sb	a5,14(sp)
	lbu	a4,13(sp)
	lbu	a5,14(sp)
	and	a5,a5,a4
	sb	a5,15(sp)
	addi	sp,sp,16
	jr	ra
	.size	main, .-main
	.ident	"GCC: (GNU) 8.2.0"
