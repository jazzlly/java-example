local bag = {}

-- 所有方法保存到meta表中
local bagmt = {
    put = function (tbl, item)
       table.insert(tbl.items, item) 
    end,
    take = function (tbl)
        return table.remove(tbl.items)
    end,
    list = function (tbl)
        return table.concat(tbl.items, ",")
    end,
    clear = function (tbl)
        tbl.items = {}
    end
}

bagmt['__index'] = bagmt

function bag.new()
    local t = {
        items = {}
    }

    setmetatable(t, bagmt)
    return t
end


local b = bag.new()
b:put("haha")
b:put("xixi")
b:put("foo")

print(b:list())
print(b.items[1])

print(b:take())
print(b:list())