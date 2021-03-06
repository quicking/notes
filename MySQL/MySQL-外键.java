------------------------
MYSQL-外键				|
------------------------
	# foreign key
	# 实际开发中很少用外键,一般都是由程序来维护关系
	# 起码目前我呆过的四家公司都是这个德行(好维护)
	# 就是当表的本身这个字段(非主键)的值,是来自于其他表的一个字段	


------------------------
MYSQL-添加外键			|
------------------------
	1,在创建表的时候添加
		creat table [表名称](
			id varchar(50) primary key,
			dep_no varchar(50) 
			foreign key(dep_no) references dept(dep_id);
		)
	
		# foreign key([本表字段]) references [其他表名]([其他表主键]);
		# 外键,要求字段本身必须是一个索引,如果字段本身没有索引
		  外键会先创建一个索引,然后才会创建外键本身
		# 默认的外键名称会:fpk1,fk2....依次递增

	2,在创建表后添加
		# 要考虑数据的问题
		alter table [表名] add constraint [外键名称] foreign key([外键字段]) references [其他表名]([其他表主键]);

	* 一张表中可以有多个外键,但是名字不能相同

------------------------
MYSQL-删除&修改外键		|
------------------------
	# 其实没有修改这一个说话,只能先删除后创建
	# alter table [表名] drop foreign key [外键名];
		* 一张表中可以有多个外键,但是名字不能相同
		* 删除一个外键,'没有办法从结构上看出来,要看创建语句'.

------------------------
MYSQL-外键约束			|
------------------------
	# 外键的默认作用有两个
		1,对于父表
			* 付别数据进行删除,修改.而且都涉及到主键本身
			* 如果对应的主键在子表已经有数据引用,都会操作失败
		2,对于子表(外键字段所在的表)
			* 对与数据进行写操作的时候,如果在对应的父表找不到对应的匹配,会操作失败

------------------------
MYSQL-外键条件			|
------------------------	
	# 创建外键,要有条件
	1,要保证表的存储引擎是:innodb
	2,外键字段的类型,必须与父表的主键类型一致,不然无法引用
	3,一张表中的外键名不能重复
	4,增加外键的字段,必须保证与主表对应

------------------------
MYSQL-外键约束			|
------------------------
	# 就是指外键的作用
	# 可以对外键的需求,进行定制操作
	# 约束模式(都是针对父表的约束)
		1,district
			* 严格模式(默认)
			* 当子表,引用了父表的主键.那么父表该主键的记录不能删除,也不能更新该主键
		2,Cascade
			* 级联模式
			* 父表的操作,对应的子表关联的数据也会跟着操作.父表中的数据删除,子表也会删除.更新同理
		3,set null
			* 置空操作
			* 父表执行了更新或者删除之后,子表对应的记录置空.('仅仅是引用的那个字段置空')
			* 该模式的条件就是'当前字段,允许为null.如果不满足.外键无法创建'
	# 约束语法
		foreign key([外键字段]) references [父表]([父表主键字段]) on delete [模式] update [模式]
	


	