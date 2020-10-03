set_global_assignment -entity "FP_S2D" -library "lib_FP_S2D" -name IP_TOOL_NAME "altera_fp_functions"
set_global_assignment -entity "FP_S2D" -library "lib_FP_S2D" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "FP_S2D" -library "lib_FP_S2D" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_FP_S2D" -name SPD_FILE [file join $::quartus(sip_path) "FP_S2D.spd"]

set_global_assignment -library "lib_FP_S2D" -name MISC_FILE [file join $::quartus(sip_path) "FP_S2D_sim/dspba_library_package.vhd"]
set_global_assignment -library "lib_FP_S2D" -name MISC_FILE [file join $::quartus(sip_path) "FP_S2D_sim/dspba_library.vhd"]
set_global_assignment -library "lib_FP_S2D" -name MISC_FILE [file join $::quartus(sip_path) "FP_S2D_sim/FP_S2D.vhd"]
