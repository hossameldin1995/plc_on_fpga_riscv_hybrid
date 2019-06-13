	.file	"Test_Project.c"
	.option nopic
	.text
	.section	.text.startup,"ax",@progbits
	.align	2
	.globl	main
	.type	main, @function
main:
	li	a5,478
	sw	a5,12(zero)
	li	a0,0
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 8.2.0"
