print("hello world")

-- 没有声明的变量是nil
print('nil is:', nil)

print('undefined variable is:', c) -- nill

-------------------------------------------------------

-- 局部变量
local a = 1

local x, y = 1, 2

-- 全局变量, 首字母大写
GlobVar = 2

-- 16进制
local hex = 0x11

----------------------------------------------------
-- string

local s = 'abc\txyz' -- 支持转义字符

local multiline = [[multiline string
xixi
haha
wahaha
heihei
\t\thaha
]] -- 多行字符串中转移字符是无效的

print(s)
print(multiline)

-- ..用于连接字符串
print(multiline .. s)

-- 字符串转换
s = tostring(10)
a = tonumber(s)

print("len of string:", s, "is:", #s)


local ss = string.char(0x31, 0x32, 0x33, 0x34)
print(ss)

print(string.byte(ss, 2))

--------------------------------------------------
-- 函数

-- 参数
local function foo(v1, v2)
    print(v1, v2)
end

foo(1, 2)

-- 返回值， 不返回为nil
local function bar(v1, v2, v3)
    return v1 + v2 + v3
end

print(bar(1, 2, 3))

-- 可以返回多个值
local function haha(v1, v2, v3)
    return v3, v2, v1
end

local i, j, k = haha(1, 2, 3)
print(i, j, k)

------------------------------------------------
-- table

require 'tableUtils'

-- 数值下标, 下标从1开始
-- 内部可以保存数值，字符串, table, 函数
local t1 = { 1, 'hello', { 'foo', 'bar' }, function(v1, v2) print(v1, v2) end }
t1[4](1, 2)

table.insert(t1, "tail")
table.insert(t1, 2, '222')

print('len of table:', #t1)
print('content of table:', DumpTable(t1, 2))

-- table可以使用字符串作为下标
require 'tableUtils'

local ts = {
    a = 1,
    b = "bar",
    c = {
        cc = 'heihei',
        cf = function(v1, v2)
            return v1 .. v2
        end
    }
}
print('content of table:', DumpTable(ts, 2))
print(ts['c']['cf']('xixi', 'haha'))

-- 使用无引号下标访问
print(ts.c.cf(1, 2))

------------------------------------------------
-- 全局表

-- 所有全局变量都在全局表中
G1 = 1
print(_G.G1)
print(_G.table.insert)


-------------------------------------------------
-- 布尔类型
-- false 和 nil表示假
-- 其他包括0, 都是真

local t = true
local f = false

print(t and f)
print(t or f)
print(not f)

print(nil and true) -- nil
print(nil or false) -- false
print(not nil) -- true

print(1 >= 2)
print(1 <= 2)
print(1 ~= 2)

-- 问号表达式
print(1 > 0 and 'yes' or 'no')
print(1 < 0 and 'yes' or 'no')

------------------------------------------------
-- if

if 0 then
    print('0 is true!')
end

if 1 > 10 then
    print('haha')
elseif 1 > 5 then
    print('xixi')
else
    print('heihei')
end

-----------------------------------------------
-- loop

for i = 1, 10, 2 do
    print('index:', i)
    if i == 7 then
        break
    end
end

local cnt = 10
while cnt > 0 do
    print(cnt)
    cnt = cnt - 1

    if cnt == 2 then
        break
    end
end