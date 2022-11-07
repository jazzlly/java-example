
-- 为表a设置方法
local t = {a = 1}

local mt = {
    __add = function (a,b)
        return a.a + b
    end,
    -- index可以是函数，也可以是表
    -- __index = function (tbl, key)
    --     return 'haha'
    -- end
    __index = {
        haha = 123,
        xixi = 234
    }
}

setmetatable(t, mt)

-- 调用__add元方法
print(t+1)

-- 调用__index元方法
print(t.haha)
print(t.xixi)
