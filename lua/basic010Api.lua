local hello = require "testdir.hello"


print(string.find("hello world", 'world'))

print(math.abs(-1))


-- require 应用其他文件
-- require('hello') , 应用同级目录下hello.lua
-- 多级目录使用.连接
-- 只会运行一次
-- 会在package.path中查找

-- 只会运行一次
require('testdir.hello')
require('testdir.hello')

hello.say()

print(package.path)


local t = {1, 2, 3, 4, 5}
for i = 1, #t do
    print(t[i])
end

-- 迭代器, ipairs访问数字下标的table
for index, value in ipairs(t) do
    print(index, value)
end

local tt = {
    a = 'aa',
    b = 'bb',
    c = 'cc'
}

-- 迭代器，pairs访问字符下标的table
for key, value in pairs(tt) do
    print(key, value)
end

-- 模拟迭代器
local k, v = next(tt)
while k do
    print("key:", k, "value:", v)
    k,v = next(tt, k)
end

-- 可以使用next判断table是否为空
print(next({}))  -- nil

