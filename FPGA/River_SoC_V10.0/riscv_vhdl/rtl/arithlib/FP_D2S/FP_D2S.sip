set_global_assignment -entity "FP_D2S" -library "lib_FP_D2S" -name IP_TOOL_NAME "altera_fp_functions"
set_global_assignment -entity "FP_D2S" -library "lib_FP_D2S" -name IP_TOOL_VERSION "18.0"
set_global_assignment -entity "FP_D2S" -library "lib_FP_D2S" -name IP_TOOL_ENV "mwpim"
set_global_assignment -library "lib_FP_D2S" -name SPD_FILE [file join $::quartus(sip_path) "FP_D2S.spd"]

set_global_assignment -library "lib_FP_D2S" -name MISC_FILE [file join $::quartus(sip_path) "FP_D2S_sim/dspba_library_package.vhd"]
set_global_assignment -library "lib_FP_D2S" -name MISC_FILE [file join $::quartus(sip_path) "FP_D2S_sim/dspba_library.vhd"]
set_global_assignment -library "lib_FP_D2S" -name MISC_FILE [file join $::quartus(sip_path) "FP_D2S_sim/FP_D2S.vhd"]
