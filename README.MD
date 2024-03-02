# FirstJoinKits

## 介绍

一款可以自定义初次进入服务器发放物品、执行指令的插件轻量级插件

本插件仍在测试，如有问题，请移步至 [Issues](https://github.com/TeamVastsea/FirstJoinKits/issues) 或 [Pull Requests](https://github.com/TeamVastsea/FirstJoinKits/pulls)

## 前置插件

- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)

## 示例文件

```yaml
message: "Welcome to this server! &bItems has been added to your inventory."
items:
  - material: "apple"
    display: "love"
    amount: 20
    lore:
      - "Welcome to this server"
      - "This is a warm welcome from admin"
      - "Have a nice day :)"
    enchantment:
      - id: "arrow_infinite"
        level: 3
  - material: "paper"
    display: "hahaha"
    amount: 3
    lore:
      - "Hello"
      - "Please &bread instructions"
  - material: "diamond_sword"
    enchantment:
      - id: "fire_aspect"
        level: 3
commands:
  - "say new player joined at %player_biome%!"
  - "kill %player_name%"
```

## 效果预览

![preview.jpg](https://s2.loli.net/2024/03/02/S7RF35tXTpdnCBk.png)

## 指令

- `/firstjoinkits reload` 重载配置文件
- `/firstjoinkits give` <玩家> 发放物品

## 权限节点

| 权限节点                  | 描述                            |
|-----------------------|-------------------------------|
| firstjoinkits.*       | FirstJoinKits 管理员权限           |
| firstjoinkits.command | 允许使用 /firstjoinkits 命令        |
| firstjoinkits.reload  | 允许使用 /firstjoinkits reload 命令 |
| firstjoinkits.give    | 允许使用 /firstjoinkits give 命令   |

## 相关链接

- [GitHub](https://github.com/TeamVastsea/FirstJoinKits)