IF COL_LENGTH('Zhurong_ProductionOrder', 'Image') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD [Image] NVARCHAR(1000) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData1') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData1 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData2') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData2 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData3') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData3 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData4') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData4 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData5') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData5 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData6') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData6 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData7') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData7 NVARCHAR(255) NULL;
END

IF COL_LENGTH('Zhurong_ProductionOrder', 'UData8') IS NULL
BEGIN
    ALTER TABLE Zhurong_ProductionOrder ADD UData8 NVARCHAR(255) NULL;
END
