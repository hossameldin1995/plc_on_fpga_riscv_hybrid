restart

onerror {resume}
quietly WaveActivateNextPane {} 0
add wave -noupdate /axi4_pwm/clk
add wave -noupdate /axi4_pwm/clk_pwm
add wave -noupdate /axi4_pwm/nrst
add wave -noupdate /axi4_pwm/wb_dev_rdata
add wave -noupdate /axi4_pwm/w_bus_re
add wave -noupdate /axi4_pwm/w_bus_we
add wave -noupdate /axi4_pwm/wb_bus_wdata
add wave -noupdate /axi4_pwm/EN
add wave -noupdate /axi4_pwm/Q
add wave -noupdate /axi4_pwm/DC
add wave -noupdate /axi4_pwm/Frq
add wave -noupdate /axi4_pwm/T_Count
add wave -noupdate /axi4_pwm/Comp_Count
add wave -noupdate /axi4_pwm/rst
add wave -noupdate /axi4_pwm/data
add wave -noupdate /axi4_pwm/rdreq
add wave -noupdate /axi4_pwm/wrreq
add wave -noupdate /axi4_pwm/q_FIFO
add wave -noupdate /axi4_pwm/rdempty
add wave -noupdate /axi4_pwm/wrempty
TreeUpdate [SetDefaultTree]
WaveRestoreCursors {{Cursor 1} {187677 ps} 0}
quietly wave cursor active 1
configure wave -namecolwidth 184
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
WaveRestoreZoom {0 ps} {1675688 ps}

force -freeze sim:/axi4_pwm/clk 1 0, 0 {6666 ps} -r 13333.33333
force -freeze sim:/axi4_pwm/clk_pwm 1 0, 0 {500000 ps} -r 1000000
force -freeze sim:/axi4_pwm/nrst 0 0
force -freeze sim:/axi4_pwm/data 856 0

run
run

force -freeze sim:/axi4_pwm/nrst 1 0
