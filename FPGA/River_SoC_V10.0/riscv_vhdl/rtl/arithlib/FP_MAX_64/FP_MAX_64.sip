set_global_assignment -entity "FP_MAX_64" -library "lib_FP_MAX_64" -name IP_TOOL_NAME "altera_fp_functions"
set_global_assignment -entity "FP_MAX_64" -library "lib_FP_MAX_64" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "FP_MAX_64" -library "lib_FP_MAX_64" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_FP_MAX_64" -name SPD_FILE [file join $::quartus(sip_path) "FP_MAX_64.spd"]

set_global_assignment -library "lib_FP_MAX_64" -name MISC_FILE [file join $::quartus(sip_path) "FP_MAX_64_sim/dspba_library_package.vhd"]
set_global_assignment -library "lib_FP_MAX_64" -name MISC_FILE [file join $::quartus(sip_path) "FP_MAX_64_sim/dspba_library.vhd"]
set_global_assignment -library "lib_FP_MAX_64" -name MISC_FILE [file join $::quartus(sip_path) "FP_MAX_64_sim/FP_MAX_64.vhd"]
