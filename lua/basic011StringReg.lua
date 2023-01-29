

-- 查找字符串，并返回开始结束位置
print(string.find("1234hahaxixi", '234'))
print(string.find("1234hahaxixi", 'foo'))  -- not found, nil


-- 查找字符串，返回找到的字符串
print(string.match('1234xixihaha', 'xixi'))

-- lua正则使用%作为转义字符
print(string.find("1234hahaxixi", '2.4'))

print(string.find("1234..hahaxixi", '4%.%.h'))

print(string.find("1234..hahaxi%xi", 'xi%%%l'))

-- 匹配多种
print(string.find('1234h1d.a', '4[%d%l%p]+'))

-- group
print(string.match("1234%haha-xixi", '(%d+).*-(%l+)'))

-- gmath
local hellolua = 'hello world from lua'
for w in string.gmatch(hellolua, '%a+') do
    print(w)
end