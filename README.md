# ColorBlindness

[![Curseforge](http://cf.way2muchnoise.eu/full_colorblindness_downloads(0D0D0D-F16436-fff-010101-fff).svg)](https://www.curseforge.com/minecraft/mc-mods/colorblindness)
[![Curseforge](http://cf.way2muchnoise.eu/versions/For%20MC_colorblindness_all(0D0D0D-F16436-fff-010101).svg)](https://www.curseforge.com/minecraft/mc-mods/colorblindness/files)
[![Modrinth](https://img.shields.io/modrinth/dt/OLtvxS9Q?label=Modrinth&logo=modrinth)](https://modrinth.com/mod/colorblindness)
[![CI/CD](https://github.com/cech12/ColorBlindness/actions/workflows/cicd-workflow.yml/badge.svg)](https://github.com/cech12/ColorBlindness/actions/workflows/cicd-workflow.yml)
[![CodeFactor](https://www.codefactor.io/repository/github/cech12/colorblindness/badge)](https://www.codefactor.io/repository/github/cech12/colorblindness)
[![gitlocalized ](https://gitlocalize.com/repo/8143/whole_project/badge.svg)](https://gitlocalize.com/repo/8143/?utm_source=badge)
[![License](https://img.shields.io/github/license/cech12/ColorBlindness)](http://opensource.org/licenses/MIT)
[![](https://img.shields.io/discord/752506676719910963.svg?style=flat&color=informational&logo=discord&label=Discord)](https://discord.gg/gRUFH5t)

ColorBlindness is a **Minecraft Forge** mod. The purpose is to provide color blindness potion effects for other mods to add their own potions or items with color blindness effects.

## Effects

- **Achromatomaly**: Color weakness
- **Achromatopsia**: Color blindness
- **Deuteranomaly**: Green weakness
- **Deuteranopia**: Green blindness
- **Protanomaly**: Red weakness
- **Protanopia**: Red blindness
- **Tritanomaly**: Blue weakness
- **Tritanopia**: Blue blindness

## In-game usage

Type the following line to give you the Achromatopsia effect for 10 seconds.

    /effect give YourName colorblindness:achromatopsia 10

## Import to your project

[![](https://jitpack.io/v/cech12/ColorBlindness.svg)](https://jitpack.io/#cech12/ColorBlindness)

Add the following to your `build.gradle` file:

### since 1.20.4-3.0.0.0

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation fg.deobf("com.github.cech12:ColorBlindness:${loader}:${colorblindness_version}")
}
```

Replace `${loader}` with the loader (`forge` or `neoforge`) you want to use.

Replace `${colorblindness_version}` with the version of ColorBlindness that you want to use. The actual versions can be found on the Github Releases page.

### before 1.20.4-3.0.0.0

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation fg.deobf("com.github.cech12:ColorBlindness:${colorblindness_version}")
}
```

Replace `${colorblindness_version}` with the version of ColorBlindness that you want to use. The actual versions can be found on the Github Releases page.
