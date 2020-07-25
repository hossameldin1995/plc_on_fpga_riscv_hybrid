/******************************************************************************
 * @file
 * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.
 * @author    Sergey Khabarov - sergeykhbr@gmail.com
 * @brief     AXI4 system bus constants definition
******************************************************************************/

#ifndef __AXI_CONST_H__
#define __AXI_CONST_H__

#include <inttypes.h>

typedef uint64_t adr_type;

static const int AXI4_SYSTEM_CLOCK = SYS_HZ;

#define CFG_NASTI_MASTER_CACHED     0
#define CFG_NASTI_MASTER_UNCACHED   1
#define CFG_NASTI_MASTER_TOTAL      2

#define MST_DID_EMPTY             0x7755
#define SLV_DID_EMPTY             0x5577

// Masters IDs
#define RISCV_CACHED_TILELINK     0x0500
#define RISCV_UNCACHED_TILELINK   0x0501


#ifdef WIN32
#ifdef __cplusplus
extern "C" {
#endif
void WRITE32(const volatile uint32_t *adr, uint32_t val);
void WRITE64(const volatile uint64_t *adr, uint64_t val);
uint8_t READ8(const volatile uint8_t *adr);
uint16_t READ16(const volatile uint16_t *adr);
uint32_t READ32(const volatile uint32_t *adr);
uint64_t READ64(const volatile uint64_t *adr);
#ifdef __cplusplus
}
#endif

#else

static inline void WRITE32(const volatile uint32_t *adr, uint32_t val) {
    *((volatile uint32_t *)adr) = val;
}

static inline void WRITE64(const volatile uint64_t *adr, uint64_t val) {
    *((volatile uint64_t *)adr) = val;
}

static inline uint8_t READ8(const volatile uint8_t *adr) {
    return adr[0];
}

static inline uint16_t READ16(const volatile uint16_t *adr) {
    return adr[0];
}

static inline uint32_t READ32(const volatile uint32_t *adr) {
    return adr[0];
}

static inline uint64_t READ64(const volatile uint64_t *adr) {
    return adr[0];
}

#endif              

#endif  // __AXI_CONST_H__