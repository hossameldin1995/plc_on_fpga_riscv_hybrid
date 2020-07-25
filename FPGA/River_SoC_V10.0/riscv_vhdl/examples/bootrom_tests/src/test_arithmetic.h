#ifndef __BOOTROM_TESTS_SRC_TEST_FPU_H__
#define __BOOTROM_TESTS_SRC_TEST_FPU_H__

#include <inttypes.h>


static const uint32_t test_int_mul[] = {
    0,
    3,
    6,
    9,
    12,
    15,
    18,
    21,
    24,
    27,
    30
};

static const uint32_t test_int_div[] = {
    0,
    0,
    0,
    1,
    1,
    1,
    2,
    2,
    2,
    3,
    3
};

static const double test_double_mul[] = {
    0.00,
    3.20,
    6.40,
    9.60,
    12.8,
    16.0,
    19.2,
    22.4,
    25.6,
    28.8,
    32.0
};

static const double test_double_div[] = {
    0.0000,
    0.3125,
    0.6250,
    0.9375,
    1.2500,
    1.5625,
    1.8750,
    2.1875,
    2.5000,
    2.8125,
    3.1250
};

#endif



