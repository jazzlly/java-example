
local co1 = coroutine.create( function (val)
    for i = 1, 10, 1 do
        os.execute("sleep 1")
        print("hello coroutine 11!", val)
        local v2 = coroutine.yield('a', 'b', 'c') -- yield输入参数会转换为resume的返回值
        print("hello coroutine 12!", v2)

    end
    
end )

local co2 = coroutine.create( function (val)
    for i = 1, 10, 1 do
        os.execute("sleep 1")
        print("hello coroutine 21!", val)
        local v2 = coroutine.yield(1,2,3)
        print("hello coroutine 22!", v2)
    end
    
end )

print(coroutine.resume(co1, 'foo')) -- 第一次resume的参数会输入到create
print(coroutine.resume(co2, 'bar'))

for i = 1, 10, 1 do
    os.execute("sleep 1")
    print("in main:")

    print(coroutine.resume(co1, 'foo'..i)) -- 第二次resume的参数会变成到yield的返回值
    print(coroutine.resume(co2, 'bar'..i))
end
