set_global_assignment -entity "SysPLL_c5g" -library "lib_SysPLL_c5g" -name IP_TOOL_NAME "altera_pll"
set_global_assignment -entity "SysPLL_c5g" -library "lib_SysPLL_c5g" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "SysPLL_c5g" -library "lib_SysPLL_c5g" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_SysPLL_c5g" -name SPD_FILE [file join $::quartus(sip_path) "SysPLL_c5g.spd"]

set_global_assignment -library "lib_SysPLL_c5g" -name MISC_FILE [file join $::quartus(sip_path) "SysPLL_c5g_sim/SysPLL_c5g.vho"]
