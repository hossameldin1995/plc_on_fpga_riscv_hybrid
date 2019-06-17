set_global_assignment -entity "clock_generator" -library "lib_clock_generator" -name IP_TOOL_NAME "altera_pll"
set_global_assignment -entity "clock_generator" -library "lib_clock_generator" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "clock_generator" -library "lib_clock_generator" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_clock_generator" -name SPD_FILE [file join $::quartus(sip_path) "clock_generator.spd"]

set_global_assignment -library "lib_clock_generator" -name MISC_FILE [file join $::quartus(sip_path) "clock_generator_sim/clock_generator.vho"]
