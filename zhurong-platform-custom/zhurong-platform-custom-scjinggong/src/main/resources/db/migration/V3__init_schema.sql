alter TABLE dbo.Zhurong_Scjinggong_OrderItem add prd_name nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add mat_ref nvarchar(40) not null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add thickness float not null;
