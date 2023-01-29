
-- 字符串是按照byte存储。可以存储任意byte, 包括0x00
local ssss = string.char(0x53, 0x54, 0x00, 0x12)
print(#ssss)


-- 字符串序号: 正序 1, 2, 3, ...; 逆序-1, -2, -3
local str = "123456789"
print(string.byte( str, 1))
print(string.byte( str, -1))

print(string.byte( str, 1, 5 ))
print(str:byte(1, 5))  -- 上面语句的语法糖
print(str:byte(1, -1))

-- string.byte的逆运算
local sc = string.char(str:byte(1, -1))
print(sc)


local s2 = "123456789"
print(string.format("0x%x, %d, %c", s2:byte(1), s2:byte(1), s2:byte(1)))


-- 将数据(int, long, double, ...)打包为byte stream
-- <是小端， =是本地cpu字节序; 格式代码参见 https://wiki.luatos.com/_static/lua53doc/manual.html#6.4.2
local s3 = string.pack('=i', 0x12345678)
print(s3:byte(1, -1))
-- 0x78, 0x56, 0x34, 0x12
print(string.format("0x%x, 0x%x, 0x%x, 0x%x", s3:byte(1, -1)))

-- numbers to string
local s4 = string.pack("=ii", 10, 20)
print(s4:byte(1, -1))


local s5 = string.pack('=ii', 10, 20)
print(s5:byte(1, -1))
print(#s5)

-- string to numbers
print(string.unpack("=i", s5))
local v1, p1 = string.unpack("=i", s5)
print(type(v1), type(p1)) -- number, number
print(string.unpack("=i", s5, 5))
-- print(string.unpack("=i", s5, 9))

print(string.rep("haha", 5, ','))


-- 字节序
-- string.pack 将各种类型的number, 包括int, uint, short等等转化为string(byte[])
-- c: uint32_t n = 0x12345678
local data = string.pack('<L', 0x12345678)
local data2 = string.char(0x78, 0x56, 0x34, 0x12)
print(#data, #data2)
print(type(data), type(data2))   -- string, lua使用string表示byte[]
print(data:byte(1, -1))
print(data2:byte(1, -1))

-- string.unpack将string(byte[])转化为number
local aunit32 = string.unpack("<L", data)
print(type(aunit32), aunit32, 0x12345678) 

local u1, u2 = string.unpack("<LL", data..data)
print(u1, u2)


--[[
将数据填充到C结构体
typedef struct test {
    unsigned char c1,
    unsigned char c2,
    unsigned short s1
}
]]
local c1, c2, s1 = string.unpack('<BBH', data)
print(c1, c2, s1) -- 0x78, 0x56, 0x1234
