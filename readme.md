 <div align="center">
  <h1>@tolutronics/capacitor-sqlite</h1>
  <p>SQLite Storage Plugin for Capacitor</p>
</div>

<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">CAPACITOR SQLITE STORAGE</h3>
<p align="center"><strong><code>@tolutronics/capacitor-sqlite</code></strong></p>
<br>
<p align="center" style="font-size:50px;color:green"><strong>CAPACITOR 7</strong></p><br>
<br>
<p align="center">
  A Capacitor plugin providing SQLite-based key-value storage for simple data of <strong>type string only</strong> across iOS, Android, Electron, and Web platforms.</p>

## Introduction

**Note**: This is a fork of the [@capacitor-community/capacitor-data-storage-sqlite](https://github.com/capacitor-community/capacitor-data-storage-sqlite) plugin (originally created by [Jean Pierre Quéau](https://github.com/jepiqueau) and maintained by [Capgo](https://github.com/Cap-go)). The fork was created to:

- **Maintain Capacitor 7 compatibility**: Ensure the plugin works seamlessly with the latest Capacitor versions
- **Active development and maintenance**: Provide timely updates and bug fixes
- **Enhanced stability**: Improve reliability across all platforms (iOS, Android, Web, Electron)
- **Community support**: Offer faster response to issues and feature requests
- **Custom improvements**: Add features and optimizations specific to project requirements

<p align="center">
  <img src="https://img.shields.io/maintenance/yes/2024?style=flat-square" />
  <a href="https://github.com/Cap-go/capacitor-data-storage-sqlite/actions?query=workflow%3A%22CI%22"><img src="https://img.shields.io/github/workflow/status/Cap-go/capacitor-data-storage-sqlite/CI?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/Cap-go/capacitor-data-storage-sqlite"><img src="https://img.shields.io/npm/l/capacitor-data-storage-sqlite.svg?style=flat-square" /></a>
<br>
  <a href="https://www.npmjs.com/package/Cap-go/capacitor-data-storage-sqlite"><img src="https://img.shields.io/npm/dw/capacitor-data-storage-sqlite?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/Cap-go/capacitor-data-storage-sqlite"><img src="https://img.shields.io/npm/v/capacitor-data-storage-sqlite?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
<a href="#contributors-"><img src="https://img.shields.io/badge/all%20contributors-4-orange?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:END -->
</p>

## Maintainers

| Maintainer    | GitHub                                      | Social |
| ------------- | ------------------------------------------- | ------ |
| Tolutronics   | [tolutronics](https://github.com/tolutronics) |        |

## Key Features

- **🔐 Encrypted Storage**: SQLCipher support for encrypted databases (iOS & Android)
- **🌐 Cross-Platform**: Works on iOS, Android, Electron, and Web
- **⚡ High Performance**: SQLite-based storage for fast read/write operations
- **💾 Persistent Storage**: Data persists across app restarts and updates
- **📦 Simple Key-Value API**: Easy-to-use string-based storage interface
- **🔄 Import/Export**: JSON import/export functionality for data migration
- **📊 Multiple Tables**: Support for multiple tables within a database
- **🎯 Type-Safe**: Full TypeScript support with detailed type definitions
- **🔒 Transaction Safety**: Improved transaction handling for data integrity
- **📱 Web Support**: LocalForage-based implementation for web platform

## Recent Updates

### Version 6.0.x (Latest - Capacitor 7)
- **Capacitor 7 Support**: Full compatibility with Capacitor 7 framework
- **Dependency Updates**: Updated to latest SQLCipher and androidx.sqlite versions
- **Performance Improvements**: Database connections optimized for better performance
- **Enhanced Stability**: Multiple bug fixes for Android single quotes handling
- **CI/CD Improvements**: Automated testing and deployment pipelines
- **Build System Updates**: Modernized build scripts and formatting

### Version 5.0.x (Capacitor 5)
- **Capacitor 5 Compatibility**: Updated for Capacitor 5 framework
- **Android modernization**: Migrated to androidx.sqlite for better compatibility
- **Improved Error Handling**: Enhanced try-catch error handling across platforms

### Version 4.x and Earlier
- **Database Encryption**: SQLCipher integration for iOS and Android
- **Import/Export Features**: JSON-based data import/export functionality
- **Multi-table Support**: Added support for multiple tables per database
- **Electron Support**: sqlite3-based implementation for Electron platform
- **Web Platform**: LocalForage integration for browser storage

## Browser Support

The plugin follows the guidelines from the `Capacitor Team`,

- [Capacitor Browser Support](https://capacitorjs.com/docs/v3/web#browser-support)

meaning that it will not work in IE11 without additional JavaScript transformations, e.g. with [Babel](https://babeljs.io/).

## Installation

```bash
npm install --save @tolutronics/capacitor-sqlite
npx cap sync
```

- On iOS, no further steps are needed.

- On Android, no further steps are needed.

- On Web, 
```bash
npm install --save localforage
```

- On Electron
```bash
npm install --save @capacitor-community/electron
npx cap add @capacitor-community/electron
```
Go to the Electron folder of your application

```bash
cd electron
npm install --save sqlite3
npm install --save-dev @types/sqlite3
npm run build
cd ..
npx cap sync @capacitor-community/electron
```

Then build YOUR_APPLICATION

```
npm run build
npx cap copy
npx cap copy @capacitor-community/electron
npx cap open ios
npx cap open android
npx cap open @capacitor-community/electron
ionic serve
```

## Configuration

No configuration required for this plugin

## Supported methods

| Name                         | Android | iOS | Electron | Web |
| :--------------------------- | :------ | :-- | :------- | :-- |
| openStore (non-encrypted DB) | ✅      | ✅  | ✅       | ✅  |
| openStore (encrypted DB)     | ✅      | ✅  | ❌       | ❌  |
| closeStore                   | ✅      | ✅  | ✅       | ❌  |
| isStoreOpen                  | ✅      | ✅  | ✅       | ❌  |
| isStoreExists                | ✅      | ✅  | ✅       | ❌  |
| deleteStore                  | ✅      | ✅  | ✅       | ❌  |
| setTable                     | ✅      | ✅  | ✅       | ✅  |
| set                          | ✅      | ✅  | ✅       | ✅  |
| get                          | ✅      | ✅  | ✅       | ✅  |
| iskey                        | ✅      | ✅  | ✅       | ✅  |
| keys                         | ✅      | ✅  | ✅       | ✅  |
| values                       | ✅      | ✅  | ✅       | ✅  |
| filtervalues                 | ✅      | ✅  | ✅       | ✅  |
| keysvalues                   | ✅      | ✅  | ✅       | ✅  |
| remove                       | ✅      | ✅  | ✅       | ✅  |
| clear                        | ✅      | ✅  | ✅       | ✅  |
| isTable                      | ✅      | ✅  | ✅       | ✅  |
| tables                       | ✅      | ✅  | ✅       | ✅  |
| deleteTable                  | ✅      | ✅  | ✅       | ❌  |
| isJsonValid                  | ✅      | ✅  | ✅       | ✅  |
| importFromJson               | ✅      | ✅  | ✅       | ✅  |
| exportToJson                 | ✅      | ✅  | ✅       | ✅  |

## Documentation

- [API_Documentation](docs/API.md)

- [USAGE_Documentation](docs/USAGE.md)

## Applications demonstrating the use of the plugin

### Ionic/Angular

- [angular-data-storage-sqlite-app-starter](https://github.com/Cap-go/angular-data-storage-sqlite-app-starter)

### Ionic/React

- [react-data-storage-sqlite-app-starter](https://github.com/Cap-go/react-data-storage-sqlite-app-starter)

### React

- [react-datastoragesqlite-app](https://github.com/Cap-go/react-datastoragesqlite-app)

### Ionic/Vue

- [vue-data-storage-sqlite-app-starter](https://github.com/Cap-go/vue-data-storage-sqlite-app-starter)

### Vue

- [vue-datastoragesqlite-app](https://github.com/Cap-go/vue-datastoragesqlite-app)

## Usage

- [see capacitor documentation](https://capacitor.ionicframework.com/docs/getting-started/with-ionic)

- [see USAGE_Documentation](https://github.com/Cap-go/capacitor-data-storage-sqlite/blob/master/docs/USAGE.md)

## Dependencies

The IOS & Android code use SQLCipher allowing for database encryption. 
The Android code is now based on `androidx.sqlite`. The database is not closed anymore after each transaction for performance improvement.
You must manage the `close` of the database before opening a new database.
The Web code use `localforage` package to store the datastore in the Browser.
The Electron code use `sqlite3`package

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/jepiqueau"><img src="https://avatars3.githubusercontent.com/u/16580653?v=4" width="100px;" alt=""/><br /><sub><b>Jean Pierre Quéau</b></sub></a><br /><a href="https://github.com/capacitor-data-storage-sqlite/commits?author=jepiqueau" title="Code">💻</a></td>
    <td align="center"><a href="https://github.com/mwpb"><img src="https://avatars.githubusercontent.com/u/12957941?v=4" width="100px;" alt=""/><br /><sub><b>Matthew Burke</b></sub></a><br /><a href="https://github.com/capacitor-data-storage-sqlite/commits?author=jepiqueau" title="Documentation">📖</a></td>    
    <td align="center"><a href="https://github.com/mwpb"><img src="https://avatars.githubusercontent.com/u/1745820?v=4" width="100px;" alt=""/><br /><sub><b>Kevin van Schaijk</b></sub></a><br /><a href="https://github.com/capacitor-data-storage-sqlite/commits?author=jepiqueau" title="Code">💻</a></td>  
    <td align="center"><a href="https://github.com/garbit"><img src="https://avatars.githubusercontent.com/u/555396?v=4" width="100px;" alt=""/><br /><sub><b>Andy Garbett</b></sub></a><br /><a href="https://github.com/capacitor-data-storage-sqlite/commits?author=jepiqueau" title="Documentation">📖</a></td>    
     
  </tr>
</table>

<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!

## Acknowledgments

This plugin is a fork of [@capacitor-community/capacitor-data-storage-sqlite](https://github.com/capacitor-community/capacitor-data-storage-sqlite). We are deeply grateful to:

- **[Jean Pierre Quéau (@jepiqueau)](https://github.com/jepiqueau)** - Original creator and architect of this excellent plugin
- **[Capgo Team](https://github.com/Cap-go)** - Maintainers who kept the plugin updated and stable
- **All original contributors** - Listed below in the contributors section

Their outstanding work created the foundation for this fork, and we continue to benefit from their expertise and dedication to the Capacitor community.

<br>

