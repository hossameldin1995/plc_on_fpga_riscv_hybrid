restart

onerror {resume}
quietly WaveActivateNextPane {} 0
add wave -noupdate /rv_fpga_plc_river/CLOCK_125_p
add wave -noupdate /rv_fpga_plc_river/CPU_RESET_n
add wave -noupdate /rv_fpga_plc_river/GPIO_IN
add wave -noupdate /rv_fpga_plc_river/GPIO_OUT
add wave -noupdate /rv_fpga_plc_river/KEY
add wave -noupdate /rv_fpga_plc_river/LEDG
add wave -noupdate /rv_fpga_plc_river/LEDR
add wave -noupdate /rv_fpga_plc_river/SW
add wave -noupdate /rv_fpga_plc_river/UART_RX
add wave -noupdate /rv_fpga_plc_river/UART_TX
add wave -noupdate /rv_fpga_plc_river/reset
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/aximi
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/aximo
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/axisi
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/axiso
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/i_clk
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/i_rst
add wave -noupdate /rv_fpga_plc_river/River_SoC/riscv_soc0/w_glob_rst
TreeUpdate [SetDefaultTree]
WaveRestoreCursors {{Cursor 1} {0 ps} 0}
quietly wave cursor active 0
configure wave -namecolwidth 318
configure wave -valuecolwidth 100
configure wave -justifyvalue left
configure wave -signalnamewidth 0
configure wave -snapdistance 10
configure wave -datasetprefix 0
configure wave -rowmargin 4
configure wave -childrowmargin 2
configure wave -gridoffset 0
configure wave -gridperiod 1
configure wave -griddelta 40
configure wave -timeline 0
configure wave -timelineunits ps
update
WaveRestoreZoom {0 ps} {7448 ps}

force -freeze sim:/rv_fpga_plc_river/CLOCK_125_p 1 0, 0 {50 ps} -r 100
force -freeze sim:/rv_fpga_plc_river/CPU_RESET_n 0 0
force -freeze sim:/rv_fpga_plc_river/GPIO_IN 0 0
force -freeze sim:/rv_fpga_plc_river/KEY 0 0
force -freeze sim:/rv_fpga_plc_river/SW 0 0
force -freeze sim:/rv_fpga_plc_river/UART_RX 0 0

run
run
run
run
run
run
run
run

force -freeze sim:/rv_fpga_plc_river/CPU_RESET_n 1 0

run
run
run
run
run
run
run
run

