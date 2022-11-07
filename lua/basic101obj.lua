
local t = {
    a = 1,
    inc = function (tbl, v)
        tbl.a = tbl.a + v
    end
}


t.inc(t, 10)
t:inc(10)   -- 上一行的语法糖

print(t.a)