set_global_assignment -entity "FP_MUL_32" -library "lib_FP_MUL_32" -name IP_TOOL_NAME "altera_fp_functions"
set_global_assignment -entity "FP_MUL_32" -library "lib_FP_MUL_32" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "FP_MUL_32" -library "lib_FP_MUL_32" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_FP_MUL_32" -name SPD_FILE [file join $::quartus(sip_path) "FP_MUL_32.spd"]

set_global_assignment -library "lib_FP_MUL_32" -name MISC_FILE [file join $::quartus(sip_path) "FP_MUL_32_sim/dspba_library_package.vhd"]
set_global_assignment -library "lib_FP_MUL_32" -name MISC_FILE [file join $::quartus(sip_path) "FP_MUL_32_sim/dspba_library.vhd"]
set_global_assignment -library "lib_FP_MUL_32" -name MISC_FILE [file join $::quartus(sip_path) "FP_MUL_32_sim/FP_MUL_32.vhd"]
